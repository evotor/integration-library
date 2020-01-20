[![](https://jitpack.io/v/evotor/integration-library.svg)](https://jitpack.io/#evotor/integration-library)
[![Gitter](https://badges.gitter.im/evotor/integration-library.svg)](https://gitter.im/evotor/integration-library.svg)

В build.gradle проекта добавьте ссылку на репозиторий jitpack:

```
allprojects {
    repositories {
        jcenter()
        maven { url 'https://jitpack.io' }
    }
}
```

в модуле `build.gradle` добавьте зависимость и укажите точную версию:

```
dependencies {
    implementation 'com.github.evotor:integration-library:v0.4.+'
}
```

и укажите minSdkVersion проекта:
```
defaultConfig {
        minSdkVersion 23
	...
}
```

В этом проекте описаны все необходимые интерфейсы, константы и пр., необходимые для работы с оборудованием на смарт-терминале Эвотор.
	Разделы проекта:

  1. Разработка приложений.  
  1.1. [SDK для принтера Эвотор](https://github.com/evotor/integration-library/blob/master/Read_me_files/README_printer.md#1011)
  
###### Более подробную информацию по разрабатке своих решений для бизнеса на платформе Эвотор, Вы можете найти на нашем сайте для разработчиков: https://developer.evotor.ru/
