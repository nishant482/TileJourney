$destination = Join-Path $PSScriptRoot '../android_app/android/app/src/main/assets/webgame'
New-Item -ItemType Directory -Force -Path $destination | Out-Null
foreach ($item in @('index.html','style.css','manifest.webmanifest','sw.js','src')) { Copy-Item -LiteralPath (Join-Path $PSScriptRoot ('../'+$item)) -Destination $destination -Recurse -Force }
$files = Get-Content -LiteralPath (Join-Path $PSScriptRoot '../assets/runtime-assets.json') -Raw | ConvertFrom-Json
foreach ($file in $files) {
 $target = Join-Path $destination ('assets/images/'+$file)
 New-Item -ItemType Directory -Force -Path (Split-Path $target -Parent) | Out-Null
 Copy-Item -LiteralPath (Join-Path $PSScriptRoot ('../assets/images/'+$file)) -Destination $target -Force
}
Copy-Item -LiteralPath (Join-Path $PSScriptRoot '../assets/images/aso/logo-512.png') -Destination (Join-Path $PSScriptRoot '../android_app/android/app/src/main/res/drawable/tile_logo.png') -Force
