# AndLogger
A lightweight logging library that supports Android application development.

# Quick start

# Step 1

Add it in your root settings.gradle at the end of repositories:

```gradle
	dependencyResolutionManagement {
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			mavenCentral()
			maven { url 'https://jitpack.io' }
		}
	}
```
# Step 2

Add the dependency

```gradle
	dependencies {
	        implementation 'com.github.MiyazKaori:AndLogger:1.0.0-alpha'
	}
```