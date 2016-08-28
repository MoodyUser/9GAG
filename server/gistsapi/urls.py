"""gistx URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/1.10/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  url(r'^$', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  url(r'^$', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.conf.urls import url, include
    2. Add a URL to urlpatterns:  url(r'^blog/', include('blog.urls'))
"""
from rest_framework import routers, serializers, viewsets
from django.conf.urls import url, include

from .models import Gist
from . import views


# Serializers define the API representation.
class GistSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = Gist
        fields = ('git_id',
                  'self_url',
                  'title',
                  'owner_name',
                  'owner_id',
                  'recommended_gists',
                  'script_url',
                  'comments',
                  'created_at',
                  'updated_at',
                  )


# ViewSets define the view behavior.
class GistViewSet(viewsets.ModelViewSet):
    queryset = Gist.objects.all()
    serializer_class = GistSerializer


# Routers provide an easy way of automatically determining the URL conf.
router = routers.DefaultRouter()
router.register(r'gists', GistViewSet)

urlpatterns = [
    # url(r'/hot^$', views.hot),
    # url(r'/fresh^$', views.fresh),
    # default
    url(r'^api/', include(router.urls)),
    url(r'^$', views.base),
]