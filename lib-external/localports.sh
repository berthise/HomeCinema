#!/bin/sh

 iptables -A PREROUTING -t nat -p tcp --dport 80 -j REDIRECT --to-port 8080
 iptables -A OUTPUT -o lo -t nat -p tcp --dport 80 -j REDIRECT --to-port 8080

 iptables -A PREROUTING -t nat -p tcp --dport 443 -j REDIRECT --to-port 8181
 iptables -A OUTPUT -o lo -t nat -p tcp --dport 443 -j REDIRECT --to-port 8181
