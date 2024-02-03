# android_pacman

## how to clone this onto your machine
1. download android studio and open it
2. click `get from vcs` and paste the url to this repo
3. sign in with your gatech github credentials
4. open local copy of this repo

## how to create your own branch
1. open the terminal in android studio
2. type the command `git branch branchname` and replace branchname with your branch's name. this creates your branch
3. type the command `git checkout branchname` and replace branchname with your branch's name. this moves you to your branch
4. type the command `git push --all -u`. this synchs your branch with the remote repo
5. now you can commit to this branch and it won't affect main

## how to config gatech username and email in android studio
1. open the terminal in android studio (view -> tool window -> terminal)
2. type `git config user.name "username"` and replace username with your gatech username
3. type `git config user.email "email"` and replace email with your gatech email

## how to make a commit to a branch
1. go to the terminal in android studio
2. make sure you are on the branch you want (it's okay if you accidentally commit something, we can revert changes)
3. type `git add .` to stage all your files for the commit
4. type `git commit -m "commit message"` and replace commit message with a descriptive message
5. type `git push`

## how to update your branch with main
1. go to the terminal in android studio
2. type `git checkout branchname` and replace branchname with the name of your branch
3. type `git merge origin/main` to merge your branch with main
4. type `git push` to push your changes to github
