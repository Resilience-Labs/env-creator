# Flow
All parameters are passed as environment variable type. **Don't use inputs**

# Inputs
- SUFIX: String (without blank). Must refer to stage or branch. Example: dev, prod, qa, test, main, master
- SECRETS: Always ```${{ toJson(secrets) }}```
- VARS: JSON with fields public and private. Public field has a JSON with String key value. Private field is an array of secrets fields.
    Example:
```
{
    "public" : {
        "FOO" : "BAR"
    },
    "private" : ["SECRET"]
}
```
# Usage
If the repo has the secrets
- FOO_DEV = "Dev secret"
- FOO_PROD = "Prod secret"

```yaml
jobs:
  # This workflow contains a single job called "build"
  build:
    # The type of runner that the job will run on
    runs-on: ubuntu-latest

    # Steps represent a sequence of tasks that will be executed as part of the job
    steps:
      - name: Extract environment variables
        id: env-creator
        uses: Resilience-Labs/secreter-injector-action@v21
        env:
          SUFIX: prod
          SECRETS: ${{ toJson(secrets) }}
          VARS: '{"public" : { "NAME" : "GONZA"},"private":["FOO"]}'
      - name: Show
        run: echo "Output ${{ steps.env-creator.outputs.env_output }}"
```

Result
```
Output {FOO:***,NAME:GONZA}
```

The FOO value is "Prod secret"