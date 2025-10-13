@echo off
title Phoenix API - Restart
color 0C

echo ============================================
echo   Reiniciando Phoenix API (Spring Boot)
echo ============================================

echo.
echo Encerrando processos na porta 8081...
for /f "tokens=5" %%a in ('netstat -ano ^| findstr :8081') do (
    taskkill /PID %%a /F >nul 2>&1
)

timeout /t 2 >nul

echo.
echo Iniciando novamente a API na porta 8081...
echo ============================================

REM Ajuste o caminho do JAR se necessário
java -jar "D:\eclipse.workspace\api-enterprise\target\api-enterprise-1.0.0.jar"

pause
