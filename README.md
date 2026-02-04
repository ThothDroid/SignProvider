[![](https://jitpack.io/v/cristmasbox/SignProvider.svg)](https://jitpack.io/#cristmasbox/SignProvider)


# SignProvider
A library that provides ancient Egyptian hieroglyphs for use with a hieroglyph renderer such as [Egyptian Writer](https://github.com/cristmasbox/Egyptian_Writer).

*This library is part of the [Egyptian Writer](https://github.com/cristmasbox/Egyptian_Writer) Android App.*

> [!TIP]
> If you want to render Hieroglyphs in Android try the [Egyptian Writer](https://github.com/cristmasbox/Egyptian_Writer) Android App or these libraries: \
> [THOTH](https://github.com/cristmasbox/THOTH) and [MAAT](https://github.com/cristmasbox/MAAT)

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
  implementation("com.github.cristmasbox:SignProvider:1.0.0")
}
```
> [!NOTE]
> For the implementation for other build systems like `Groovy` see [here](https://jitpack.io/#cristmasbox/SignProvider/)

## Implementation with `.aar` file
Download the `SignProvider_versionname.aar` file from this repository, create a `libs` folder in your project directory and paste the file there. Then add this dependency to your `build.gradle.kts` file:
```
dependencies {
  implementation(files("../libs/SignProvider_versionname.aar"))
}
```

> [!IMPORTANT]
> If you renamed the `.aar` file you also have to change the name in the dependencies

## Version Catalog
### 04.02.2026@1.0.0
This is the first release of the SignProvider library.
### latest Version
`04.02.2026@1.0.0`