# OneStep

本版本已经修复 GitHub Actions 的 `./gradlew: No such file or directory`。

原错误来自工作流执行：

    chmod +x ./gradlew
    ./gradlew ...

但项目没有提交 Gradle Wrapper。

现在工作流直接由 `gradle/actions/setup-gradle@v4` 安装 Gradle 8.13，并执行：

    gradle --no-daemon clean assembleDebug --stacktrace

同时 Android SDK 使用 `android-actions/setup-android@v4`，只请求 `platform-tools`，再显式安装 Android 36 与 Build Tools 36。

上传整个项目到 GitHub 仓库根目录后，在 Actions 中运行 `Build OneStep APK`。

成功后 Artifact 名称为 `OneStep-debug-apk`，其中是 `OneStep-debug.apk`。

UI 演示包含 1 个主窗口 + 3 个次级窗口；次级窗口之间无间隙；点击次级窗口交换主窗口；左右拖出清空对应 slot，清空后不自动补位。

注意：普通第三方 APK 无法取得系统级 WindowManager/Task 权限，因此不能保证把任意第三方 App 的真实 Activity 同时嵌入为三个独立实时窗口。系统级 OneStep 需要系统/特权集成。
