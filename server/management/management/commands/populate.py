from django.contrib.auth.models import User
from django.core.management import BaseCommand
from django.contrib.sites.models import Site
# from emessages import console as emc
# from tips import console as tc
from requests import get as _get
from pprint import pprint
from gistsapi.models import Gist
from languages.models import Languages
import languages.console as lc

class Command(BaseCommand):
    @staticmethod
    def populate():
        lc.populate()
        request = _get(r"https://api.github.com/gists")
        gists = request.json()
        languages = {}
        for language in Languages.objects.filter(parent__isnull=True).all():
            for ending in language.file_ending.split(','):
                languages[ending] = language
        for item in gists:
            Gist.save_github_api(item,languages)


    def handle(self, *args, **options):
        self.populate()
