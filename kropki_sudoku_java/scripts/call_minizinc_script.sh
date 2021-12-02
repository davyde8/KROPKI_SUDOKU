#!/bin/bash
echo "$1"
echo "$2"


x = minizinc -c --solver Gecode $1 $2

echo(x)

