# Обновление плагина до нужной версии ElasticSearch
## Ставим зависимости 
Инструкция написана для сборки под Mac OS на Apple Silicon.
Для начала установим нужную версию Java. Версию нужно выбрать такую, чтоб соответствовала трем матрицам.
1. [Матрица ElasticSearch](https://www.elastic.co/support/matrix). Здесь смотрим на нужную версию ElasticSearch и матрицу Product and JVM, например 8.10.* поддерживает Java 17, 20 и 21.
2. [Матрица Gradle](https://docs.gradle.org/current/userguide/compatibility.html). Здесь смотрим на версии Java и Kotlin, которые поддерживаются Gradle. На текущий момент доступны версии с 8 по 20, устанавливаем Java 17 так как это LTS версия.

Устанавливаем выбранную версию Java.
`brew install openjdk@17`
Теперь можно установить Gradle и Kotlin.
`brew install gradle`
`brew install kotlin`
## Экспортируем нужную версию Java для сборки
В папке проекта перед манипуляциями с Gradle выполним команду:
`export JAVA_HOME=/opt/homebrew/opt/openjdk@17/`
## Собираем Gradle Wrapper (опционально)
В папке проекта нужно запустить команду:
`gradle wrapper`

После выполнения в проекте обновятся файлы gradlew, gradlew.bat и папка gradle/wrapper. Этот пункт опциональный, так как можно попробовать запустить уже существующие скрипты.
## Cобираем плагин под нужную версию ElasticSearch
Поднимаем версию эластика в файле es.version и версию сборки плагина в project.version.

Для окончательной сборки запускаем следующую команду:
`./gradlew assemble -PesVersion=<ВЕРСИЯ_ЭЛАСТИКА>`

Собранный плагин появится по пути:
`jmorphy2-elasticsearch/build/distributions/analysis-jmorphy2.*.zip`
  
Создаем коммит с изменениями, выпускаем релиз с файлом плагина.
## Траблшутинг
При возникновении проблем можно добавить флаг --info при сборке gradle wrapper и самого плагина. 

Основные проблемы возникают на этапе сборки, если в коде не было ломающих изменений, при поиске ошибок нужно смотреть на файлы build.gradle.kts в директориях buildSrc и jmorphy2-elasticsearch.

Плагины сборки иногда требуют обновления до новых версий.
## Известные проблемы

При обновлении до новых версий плагины сборки могут менять название. Так nebula.ospackage  превратился в com.netflix.nebula.ospackage. В таком случае при сборке возникнет ошибка unknown plugin id, решается чтением релизов в поисках breaking changes. 

Kotlin может ругаться на несовместимость с версией Java, тогда нужно явно указать нужную версию туллчейна в buildSrc/build.gradle.kts.

    kotlin {
	    jvmToolchain(17)
	}
