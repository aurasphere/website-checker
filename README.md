[![Donate](https://img.shields.io/badge/Donate-PayPal-orange.svg)](https://www.paypal.com/donate/?cmd=_donations&business=8UK2BZP2K8NSS)

# Website Checker

Java application for checking a website status across different TLD through a CI server with a periodic build. In detail, the checks performed are:

- connection to the website expecting a response with HTTP code 200
- page title matching the expected title
- presence of an element with a given ID in the returned page

This simple POC tests google.it and google.com using Travis CI but it can be changed to work with any website and any CI server.

Website current status is [![Travis](https://img.shields.io/travis/aurasphere/website-checker.svg)](https://travis-ci.org/aurasphere/website-checker)

<sub>©2018 Donato Rimenti</sub>
