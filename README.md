[![](https://jitpack.io/v/ThothDroid/SignProvider.svg)](https://jitpack.io/#ThothDroid/SignProvider)


# SignProvider
A library that provides ancient Egyptian hieroglyphs for use with a hieroglyph renderer such as [Egyptian Writer](https://github.com/ThothDroid/Egyptian_Writer).

*This library is part of the [Egyptian Writer](https://github.com/ThothDroid/Egyptian_Writer) Android App.*

> [!TIP]
> If you want to render Hieroglyphs in Android try the [Egyptian Writer](https://github.com/ThothDroid/Egyptian_Writer) Android App or these libraries: \
> [THOTH](https://github.com/ThothDroid/THOTH) and [MAAT](https://github.com/ThothDroid/MAAT)

## Implementation with jitpack
Add this to your `settings.gradle.kts` at the end of repositories:
```
dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
  }
}
```
Then add this dependency to your `build.gradle.kts` file:
```
dependencies {
  implementation("com.github.ThothDroid:SignProvider:1.1.1")
}
```
> [!NOTE]
> For the implementation for other build systems like `Groovy` see [here](https://jitpack.io/#ThothDroid/SignProvider/)

## Implementation with `.aar` file
Download the [`SignProvider_versionname.aar`](https://github.com/ThothDroid/SignProvider/releases/latest) file from the latest release, create a `libs` folder in your project directory and paste the file there. Then add this dependency to your `build.gradle.kts` file:
```
dependencies {
  implementation(files("../libs/SignProvider_versionname.aar"))
}
```

> [!IMPORTANT]
> If you renamed the `.aar` file you also have to change the name in the dependencies

## Version Catalog
> [!IMPORTANT]
> Since version `16.08.2026@1.1.0` the version catalog uses the new versioning system [see here](https://medium.com/@wassimsakri/the-ultimate-guide-to-versioning-in-software-development-e846eb292a0d).
### 04.02.2026@1.0.0
This is the first release of the SignProvider library.
### 08.02.2026@1.0.1
- Removed `CsvValidationException` from methods throwable list.
- Removed Phonetic alternatives from `getAllSigns`. Now it only returns the Gardiner Codes.
### 16.02.2026@1.0.2
Removed Gardiner codes without drawable from `Drawable_Paths.csv`
### 16.08.2026@1.1.0
Added `getSignPathData()` method to get the path data of a sign by its Gardiner code or phonetics.
### 16.08.2026@1.1.1
- Changed the `getSignPathData()` method to return a `SvgData` object instead of a `String`. The `SvgData` object contains the path data, width, and height of the sign.
- Renamed `getSignPathData()` to `getSvgData()`
### latest Version
`16.08.2026@1.1.1`
