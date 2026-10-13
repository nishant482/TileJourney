# Tile Journey - Triple Match

Smart Axis game in the requested gamr directory. The visible reference was a seven-slot triple-match game over scenic backgrounds. This project recreates that layout and gameplay with locally implemented tile art and original backgrounds; it is not the original publisher source or an exact recreation of unseen screens.

## Run

npm start

Open http://localhost:8086 . Node 22 or later is used by browser verification tools. No runtime npm dependencies.

npm test checks all 3,000 seeded levels for legal tray-safe completion, covered tile rules, undo, shuffle and local save recovery.

npm run build creates dist. The browser game works offline after one successful load/cache installation. All artwork is bundled in the Android app, so Android does not need a first online load.

## Included

3,000 seeded solvable levels, five board silhouettes, stacked blocked tiles, a seven-slot sorted tray, automatic triple removal, win/loss and retry screens, undo/hint/shuffle boosters, earned coins, a daily reward, journey level selection, a profile and 25 scenery themes. Eight original scenic raster illustrations are used across 25 deliberately named lighting/color themes. Two transparent 4x4 atlases supply realistic flower/fruit motifs in three tile collections. Sound and vibration can be toggled. Optional timed mode adds a saved 180-second clock to new puzzles and pauses in menus/background. Matching tiles fly to the tray, merge three together and burst into marble particles; reduced-motion users receive shortened effects. Rate saves feedback on the device; it does not pretend to submit a public store review. Share opens the native Android/browser chooser, with a copy-message fallback. No reference-publisher URL is included. No ads, billing, remote services, account or reference-publisher links are included.

All user state (active puzzle, undo history, levels, coins, boosters, profile, themes, settings and rewards) is saved on this device: localStorage in the browser, SharedPreferences in Android through the LocalStore bridge. The game blocks non-local Android navigation. App/browser data deletion removes progress. Daily rewards use the local device date; no cross-device sync is claimed.

## Android

powershell -ExecutionPolicy Bypass -File tool/prepare-android.ps1
cd android_app
flutter build apk --release --target-platform android-arm64

The APK uses the generated development signing key and is for direct installation/testing. Before production publication, configure your private upload key and build a signed Android App Bundle. The generated Flutter wrapper hosts the native WebView; game logic runs locally in bundled JavaScript.

## Store assets

assets/images/aso/logo-512.png
assets/images/aso/feature-graphic-1024x500.png
assets/images/aso/01-... through 08-...-1080x1920.png

Eight final store screenshots are 1080x1920 (9:16), built from actual game screens. Files ending -raw.png preserve the unframed capture. Marketing sample levels use an isolated Chrome profile, not the player's normal browser data. tool/assets.cjs regenerates all exact-size store assets. Read PLAY_CONSOLE_LISTING.txt for accurate copy and support/privacy placeholders. Store ranking is not guaranteed.

## Generated image provenance

Built-in image_gen was used for eight scenery illustrations, the realistic flower/fruit atlases and logo-source-v2.png. Prompt set is saved in assets/images/IMAGE_PROMPTS.txt. Runtime tiles combine original generated object art with code-drawn ivory/golden ceramic faces; the feature graphic and screenshot compositions use the shipped game visuals.

## Verification

artifacts/browser-validation.json records pointer selection, triple matches, boosts, rewards, menus, all themes, audio, persistence, responsive layout and offline reload. Android native runtime needs a physical-device check; a successful build/signature check alone is not a device-runtime test.

Version 1.2.0 / Android versionCode 3. tools/validate-upgrade.cjs additionally verifies three-tile merging, falling marble frames, timing and pause/expiry, all tile collections, local ratings, share payload, and restart during a winning animation without duplicate rewards.


## Version 1.2.0

Home, Journey, Scenery, Tile Collections, Profile, Settings, Daily Gift and Help now have dedicated full-screen layouts with a coordinated vector icon system. Journey uses a winding level path. There are 20 tile collections and 45 scenery choices, preserving all 25 earlier choices and adding 20 new original illustrated destinations (28 unique scene illustrations total). New atlases/destinations are exported as WebP to keep runtime downloads and the APK smaller; their original generated PNGs and prompt records remain in assets/images/generated.

Matching is non-blocking. Logic commits each tap atomically; independent visual layers animate tile flight, three-tile combination and marble blasts without disabling the board or showing match text. Effects can overlap and are bounded to avoid unbounded memory growth. Save history, preferences, rewards, timers and tray rules are unchanged.

All runtime artwork is listed in assets/runtime-assets.json. Generated PNG source art is excluded from browser/Android runtime bundles. tool/validate-redesign.cjs verifies all collections, separate screens, new destinations, rapid taps during blasts, overlapping triple integrity, reward/persistence and offline art. The PNGs ending -raw.png and the eight 1080x1920 ASO compositions reflect the current UI. Generated artwork used built-in imagegen; exact prompts and sources are recorded in assets/images/generated/manifest.json.
