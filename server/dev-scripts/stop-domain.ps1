# Payara Server Domain Stop Script
# ローカル開発用 - Payara Server を停止

Write-Host "Payara Server を停止しています..." -ForegroundColor Yellow

# Payara のインストールパスを環境変数から取得
$PAYARA_HOME = $env:PAYARA_HOME
if (-not $PAYARA_HOME) {
    Write-Host "環境変数 PAYARA_HOME が設定されていません" -ForegroundColor Red
    exit 1
}

$ASADMIN = "$PAYARA_HOME\bin\asadmin.bat"

# Payara がインストールされているか確認
if (-not (Test-Path $ASADMIN)) {
    Write-Host "Payara Server が見つかりません: $ASADMIN" -ForegroundColor Red
    exit 1
}

# ドメインを停止
Write-Host "ドメインを停止中..." -ForegroundColor Yellow

& $ASADMIN stop-domain

if ($LASTEXITCODE -eq 0) {
    Write-Host "Payara Server が正常に停止しました\!" -ForegroundColor Green
} else {
    Write-Host "Payara Server の停止に失敗しました" -ForegroundColor Red
    exit 1
}