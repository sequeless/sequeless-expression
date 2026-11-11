# Contributing to sequeless-expression

## Setup

Requires JDK 25. The Maven wrapper (`./mvnw`) is committed, so no local Maven install is needed.
The devcontainer (`.devcontainer/`) provides a ready JDK 25 toolchain.

Library modules build with `--release 21` through the `sequeless-build` parent. The build JDK is
always 25. Maven reads the `github` package registry, so a read token is needed:

```bash
export PACKAGES_READ_TOKEN=<PAT with read:packages>   # locally; CI reads the org secret
make verify    # confirm a green baseline
```

Your `~/.m2/settings.xml` needs a `github` server entry (`server-id: github`) with the same PAT.

The parent POM and `kernel-values` resolve from GitHub Packages with your token. GitHub Actions
fetches the reusable workflows from `sequeless-build` at the tag pinned in `.github/workflows/`, and
Renovate fetches its preset itself; neither uses your token.

There are no local git hooks. Conventional Commits are checked in CI, not on commit.

## Workflow

1. **Branch** off `main`.
2. **Make the change** with tests. The test is the specification. Format with `make fmt`.
3. **Verify** with `make verify`.
4. **Commit** using Conventional Commits.
5. **Open a PR** following the template. The `pr-title.yml` check lints the PR title with commitlint.
6. **Merge by squash.** The PR title becomes the commit on `main`. release-please reads it.

## Conventional Commits

`feat`, `fix`, `docs`, `style`, `refactor`, `perf`, `test`, `build`, `ci`, `chore`, `revert`.
Use `feat!:` or a `BREAKING CHANGE:` footer for majors. release-please derives versions and
changelogs from these titles. Never hand-edit a `CHANGELOG.md`.

## Recording a decision (ADR)

`make new-adr title="Short title"` scaffolds the next-numbered record under `docs/adr/`. Fill in
Context, Decision and Consequences. ADRs are immutable: supersede with a new one instead of editing.

## Quality gates

Spotless (format), Enforcer (dependency hygiene, including the parent's Spring, Jena and Lombok
ban on core modules), and ArchUnit (boundaries) all run in `make verify`. CodeQL runs in CI. No
workflow in this repo runs Sonar; `sonar-project.properties` is present.

## Coverage (JaCoCo)

JaCoCo reports are generated on `verify`. No coverage threshold is enforced: this repo's POMs
configure no check execution.
