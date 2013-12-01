Gitz
====

A java based command line tool for managing multiple git repositories. 

It allows commnds to run against all repositories in a set. So if a Gitz 
session was defined to create a set of five repositories, running 'Gitz status' 
would give you the staus of all five in one go.

Gitz attempts a practical approach, only a subset of key commands are supported,
and those are policed. That is,  certain key comands are restricted in operation - 
mainly because they don't make sense or are dangerous when working on multiple 
repositories at once.

For example, 'rebase --interactive', or 'commit --amend' cannot be performed using 
Gitz, because it's likely a user would want to run these commands only against 
one of a set of repositories - not all.


