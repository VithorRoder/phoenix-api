@echo off
title Phoenix API - Stop
color 0E

echo ============================================
echo   Encerrando Phoenix API (Spring Boot)
echo ============================================

echo.
echo Encerrando processos na porta 8081...
for /f "tokens=5" %%a in ('netstat -ano ^| findstr :8081') do (
    taskkill /PID %%a /F >nul 2>&1
)

echo.
echo API encerrada com sucesso (se estava rodando).
echo ============================================

pause