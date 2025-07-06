# 100ka Project

## サーバー

### 起動コマンド

```bash
# サーバー起動（バックグラウンド）
docker compose up -d

# サーバー起動（フォアグラウンド・ログ表示）
docker compose up

# サーバー停止
docker compose down

# サーバー状態確認
docker compose ps

# ログ確認
docker compose logs payara-server
```

### 環境情報

- **アプリケーションサーバー:** Payara Server 6.2025.6
- **JDK:** OpenJDK 21 (Azul Zulu)
- **コンテナ:** Docker
