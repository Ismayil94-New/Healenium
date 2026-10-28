#!/usr/bin/env bash
set -euo pipefail
if [ ! -f upstream/docker-compose.yaml ]; then
  echo 'Fetching official Healenium Docker Compose configuration...'
  git clone --depth 1 https://github.com/healenium/healenium.git upstream
fi
# Upstream creates a Docker network called healenium.
docker compose -f upstream/docker-compose.yaml up -d
docker compose -f docker-compose.app.yml up -d
printf '\nApp: http://localhost:8090/?version=1\nChanged app: http://localhost:8090/?version=2\nReport: http://localhost:7878/healenium/report\n'
