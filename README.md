android-auth
===

[![](https://jitpack.io/v/fleury-digital/FleuryAuthAndroid.svg)](https://jitpack.io/#fleury-digital/FleuryAuthAndroid)

Gradle
===

Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.fleury-digital:FleuryAuthAndroid:1.0.0'
	} 
  
 Using
 ===
 
     val auth = AuthFleury(URL, CLIENT_ID, BRAND, CREDENTIALS)
 
 ### SigIn - Username and Password
 
    auth.signIn(username, password).observe(this, Observer {
      when (it) {
        is BaseCommand.Success -> {}
      }
    })
