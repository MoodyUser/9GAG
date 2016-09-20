import os
from datetime import datetime

from django.contrib.auth.models import User
from django.db import models

# Create your models here.
from languages.models import Languages


class Gist(models.Model):
    # Our user
    language = models.ManyToManyField(Languages)
    user = models.ForeignKey(User, related_name="gists", blank=True, null=True)
    git_id = models.CharField(max_length=40, unique=True, null=False, blank=False)
    title = models.CharField(max_length=500, null=True, blank=False)
    self_url = models.CharField(max_length=100, null=False, blank=False)
    html_url = models.CharField(max_length=100, null=False, blank=False)
    # api owner
    owner_name = models.CharField(max_length=100, null=False, blank=False)
    owner_id = models.CharField(max_length=100, null=False, blank=False)
    # api owners gists
    recommended_gists = models.CharField(max_length=100, null=False, blank=False)
    # The script url should look tike this/render the result.
    # < script src = "https://gist.github.com/itzg/d569e21b13b7a5cf15ad0ef1366354e2.js" > < / script >
    script_url = models.TextField(max_length=10000, null=False, blank=False)
    # Url Or 0 for no comments.
    comments = models.IntegerField( null=False, blank=False)
    comments_url = models.CharField(max_length=500, null=False, blank=False)
    # forks
    forks_url = models.CharField(max_length=500, null=False, blank=False)
    # likes
    like_url = models.CharField(max_length=500, null=False, blank=False)

    files_words = models.TextField(max_length=10000, null=False, blank=False)
    size = models.IntegerField(null=True, blank=True)
    created_at = models.TimeField(null=False, blank=False)
    updated_at = models.TimeField(null=False, blank=False)

    # photo = models.ImageField(upload_to="error_images",
    #                           default=os.path.join('', 'misc', 'no-image.png'),
    #                           null=True,
    #                           blank=True)


    def save_github_api(json_item, languages):
        kwargs = {
            "user": None,
            "git_id": json_item['id'],
            "title": json_item['description'],
            "self_url": json_item['url'],
            "html_url": json_item['html_url'],
            "script_url": '<script src="https://gist.github.com/{}.js"></script>'.format(
                json_item['id']),
            "comments_count": str(json_item['comments']),
            "comments_url": str(json_item.get('comments_url', "")),
            "forks_url": json_item.get('forks_url', ""),
            "likes_url": json_item["url"] + "/star",
            # "files_words": "",
            # "size = mode": "",
            # "created_at": datetime.strptime('Jun 1 2005  1:33PM', '%b %d %Y %I:%M%p')
            # TODO : created_at
            # "created_at": datetime.strptime(json_item['created_at'], "YYYY-MM-DDThh:mm:ssZ")
            "created_at": datetime.now(),
            "updated_at": datetime.now(),
        }

        if "owner" in json_item:
            kwargs["owner_name"] = json_item["owner"]['login']
            kwargs["owner_id"] = json_item["owner"]['login']
            kwargs["recommended_gists"] = "https://api.github.com/users/{}/gists".format(
                kwargs["owner_name"])
            kwargs["script_url"] = '<script src="https://gist.github.com/{}/{}.js"></script>'.format(
                kwargs["owner_name"],
                kwargs["git_id"])

        # "owner_id": "",
        # "recommended_gists": "",
        # "script_url": "",
        gist, ok = Gist.objects.update_or_create(
            git_id=json_item['id'], defaults=kwargs)
        for file in json_item['files']:
            ending = file.split(".")[-1]
            lang = languages.get(ending, None)
            if lang:
                gist.language.add(lang)
        gist.save()

    @staticmethod
    def validate_github_api(json_item):
        kwargs = {
            "user": None,
            "git_id": json_item['id'],
            "title": json_item['description'],
            "self_url": json_item['url'],

            "comments": str(json_item['comments']),
            # "files_words": "",
            # "size = mode": "",
            # "created_at": datetime.strptime('Jun 1 2005  1:33PM', '%b %d %Y %I:%M%p')
            # TODO : created_at
            # "created_at": datetime.strptime(json_item['created_at'], "YYYY-MM-DDThh:mm:ssZ")
            "created_at": datetime.now(),
            "updated_at": datetime.now(),
        }
        if "owner" in json_item:
            kwargs["owner_name"] = json_item["owner"]['login']
            kwargs["owner_id"] = json_item["owner"]['login']
            kwargs["recommended_gists"] = "https://api.github.com/users/{}/gists".format(
                kwargs["owner_name"])
            kwargs["script_url"] = '<script src="https://gist.github.com/{}/{}.js"></script>'.format(
                kwargs["owner_name"],
                kwargs["git_id"])

        # "owner_id": "",
        # "recommended_gists": "",
        # "script_url": "",
        item = Gist(**kwargs)
        item.save()

    class Meta:
        unique_together = (('user', 'git_id'),)

    def __str__(self):
        return "Gist - {}".format(self.title)
