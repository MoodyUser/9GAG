from django.contrib.auth.decorators import login_required
from django.http import HttpResponse, JsonResponse
from django.shortcuts import render, render_to_response, redirect
from rest_framework.authtoken.models import Token

# Create your views here.

@login_required()
def complete(request):
    query_string = request.GET
    if 'access_token' not in query_string:
        return redirect(r'/login/complete?access_token={}'.format(Token.objects.get_or_create(user=request.user)[0].key))
    return HttpResponse("Hello.")
