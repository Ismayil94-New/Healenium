# Selenium vs Healenium workshop

Demo target: https://automationexercise.com/login

## Prerequisites
- Java 17+, Maven, Chrome, Docker Desktop
- Healenium backend, PostgreSQL, and selector imitator running from the official Healenium `docker-compose-web.yaml`

## Run
From the project directory:

```bash
mvn test -Dtest=SeleniumWithoutHealingTest
mvn test -Dtest=SeleniumWithHealingTest#recordBaseline
mvn test -Dtest=SeleniumWithHealingTest#attemptHealing
```

The first test is **intentionally failing**. Run the baseline before the healing attempt. Healing is not guaranteed: it depends on the backend, baseline records, and element similarity.

The DOM change is temporary and occurs only in the browser. Check `http://localhost:7878/healenium/report` and PostgreSQL table `healenium.selector` for results.

## Notes
- This is a small teaching project, not a production-ready test framework.
- Cookie consent handling is included.
- Healenium Docker services are not bundled; use the official Healenium repository's Compose configuration.
