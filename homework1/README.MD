The goal of the application is to parse a JSONL file with gitub events and to create a CSV file with top five word 3-grams in the commit messages for each author name in event type “PushEvent” within the file 10K.github.jsonl.bz2. Output example:

```
'author' 'first 3-gram' 'second 3-gram' 'third 3-gram' 'fourth 3-gram' 'fifth 3-gram'
'erfankashani' 'merge pull request' 'pull request #4' 'request #4 from' 'rack from 207' 'from 207 to'
```

Event json example:
```
{
  "id": "11185375845",
  "type": "PushEvent",
  "actor": {
    "id": 10577684,
    "login": "seanwuapps",
    "display_login": "seanwuapps",
    "gravatar_id": "",
    "url": "https://api.github.com/users/seanwuapps",
    "avatar_url": "https://avatars.githubusercontent.com/u/10577684?"
  },
  "repo": {
    "id": 230676289,
    "name": "seanwuapps/soft-components",
    "url": "https://api.github.com/repos/seanwuapps/soft-components"
  },
  "payload": {
    "push_id": 4450669244,
    "size": 1,
    "distinct_size": 1,
    "ref": "refs/heads/master",
    "head": "18cf6b515f014e42588cbeadecd2ac77d8305397",
    "before": "8752d6874248dd43933a5142e3362c77c8839ff9",
    "commits": [
      {
        "sha": "18cf6b515f014e42588cbeadecd2ac77d8305397",
        "author": {
          "name": "Sean Wu",
          "email": "d7e19930cc1f42c2d0781f4d9e6f1fe5891bf9cf@seanwuapps.com"
        },
        "message": "clean up",
        "distinct": true,
        "url": "https://api.github.com/repos/seanwuapps/soft-components/commits/18cf6b515f014e42588cbeadecd2ac77d8305397"
      }
    ]
  },
  "public": true,
  "created_at": "2020-01-01T00:59:38Z"
}
```

JSONL file is placed in the root of the application with file name `10K.github.jsonl`.

You can run `main` method in `Application` class for a demo.

After running it you should be able to find an output CSV file in the root with file name `output.csv`.