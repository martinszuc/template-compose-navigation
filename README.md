# Template Compose Navigation

This repository serves as a starting point for Android projects using Jetpack Compose and Navigation. Follow the steps below to customize and set up this template for your own project.

## Getting Started

### 1. Clone the Repository

### 2. Search and Replace `EditHere`

To quickly identify the areas in the project that need customization:

- Use your IDE's search functionality to find the keyword `EditHere`.
- Replace these instances with your project-specific details, such as the app name, package name, and other custom identifiers.

### 3. Rename the Package

To rename the package structure across the entire project:

1. In your IDE (e.g., Android Studio), right-click on the root package directory (usually `com.martinszuc.templateapp`).
2. Select `Refactor` > `Rename`.
3. Choose `Rename package` and apply the changes across all files.
4. Update the package name in the `AndroidManifest.xml` file to reflect the new package name.

### 4. Update the Project Name

Rename the project to match your new app:

1. Open the `settings.gradle` and `build.gradle` files.
2. Change the `rootProject.name` to your desired project name.

### 5. Update Dependencies

Review the dependencies in the `build.gradle` files and ensure they meet your project's needs. Add or remove libraries as required.


### 6. Update the `Colors.kt` File

To customize the color scheme of your app using Material Design 3:

1. Go to the [Material Theme Builder](https://material-foundation.github.io/material-theme-builder/).
2. Design your color scheme and download the generated Kotlin files.
3. Replace the existing `Colors.kt` in the `ui/theme` directory with the Kotlin files you downloaded.
4. Ensure that the colors are correctly referenced in your `Theme.kt` file.

### 7. Update App Resources

Customize the app's resources:

- **App Name**: Change the app name in `res/values/strings.xml`.
- **Icons**: Replace the launcher icons in `res/mipmap-*`.
- **Typography**: Update the font styles in `ui/theme/Typographies.kt` using your preferred fonts.

### 8. Remove Existing Git History and Initialize a New Repository

To start fresh with a new Git history:

1. Remove the existing Git history:
```bash
rm -rf .git
git init
git remote add newrepo.git
git add .
git commit -m "Initial commit"
```

