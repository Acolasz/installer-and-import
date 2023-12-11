# Usage

## Discard

### Staged

```shell
# Staged files to unstacked files
git restore --staged .
```

### Unstaged

```shell
# Tracked files
git restore .
# or
git reset --hard
# Untracked files
git clean -f
```
