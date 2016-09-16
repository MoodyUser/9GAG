from django.db import models


# Create your models here.

class Languages(models.Model):
    # Our user
    title = models.CharField(max_length=100, null=True, blank=True)
    icon = models.CharField(max_length=500, null=True, blank=True)
    file_ending = models.CharField(max_length=10, null=True, blank=True, db_index=True)
    # parent id
    parent = models.IntegerField(blank=True, null=True)
    description = models.CharField(max_length=500, null=True, blank=True)


    def __str__(self):
        return "{} ".format(self.title)