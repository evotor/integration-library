

В build.gradle проекта добавьте ссылку на репозиторий jitpack:

```
allprojects {
    repositories {
        jcenter()
        maven { url 'https://jitpack.io' }
    }
}
```

в модуле `build.gradle` добавьте зависимость и укажите точную версию (текущая: v0.2.0):

```
dependencies {
compile 'com.github.evotor:integration-library:v0.2.0'
}
```

В этом проекте описаны все необходимые интерфейсы, константы и пр., необходимые для работы с оборудованием на смарт-терминале Эвотор.
	Разделы проекта:

  1. Разработка приложений.  
  1.1. [SDK для принтера Эвотор](https://github.com/evotor/integration-library/blob/master/Read_me_files/README_printer.md#1011)
  
###### Более подробную информацию по разрабатке своих решений для бизнеса на платформе Эвотор, Вы можете найти на нашем сайте для разработчиков: https://developer.evotor.ru/
