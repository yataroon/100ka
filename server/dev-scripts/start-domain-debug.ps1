# Payara Server Domain Debug Start Script
# ローカル開発用 - Payara Server をデバッグモードで起動

Write-Host "Payara Server をデバッグモードで起動しています..." -ForegroundColor Green

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

# ドメインをデバッグモードで起動
Write-Host "ドメインをデバッグモードで起動中..." -ForegroundColor Yellow

& $ASADMIN start-domain --debug=true --verbose

if ($LASTEXITCODE -eq 0) {
    Write-Host "Payara Server が正常に起動しました!" -ForegroundColor Green
    Write-Host "アプリケーション URL: http://localhost:8080" -ForegroundColor Cyan
    Write-Host "管理コンソール: http://localhost:4848" -ForegroundColor Cyan
    Write-Host "デバッグポート: 9009" -ForegroundColor Cyan
} else {
    Write-Host "Payara Server の起動に失敗しました" -ForegroundColor Red
    exit 1
}