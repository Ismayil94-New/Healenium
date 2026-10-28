#!/usr/bin/env bash
set -euo pipefail
docker compose -f docker-compose.app.yml down
if [ -f upstream/docker-compose.yaml ]; then docker compose -f upstream/docker-compose.yaml down; fi
