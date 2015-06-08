# Android Binding Library

This library helps to you on the process related to data binds between your model entities and your application UI, offering a bidirectional data channel.

## Version

[ ![Download](https://api.bintray.com/packages/txusballesteros/maven/android-binding/images/download.svg) ](https://bintray.com/txusballesteros/maven/android-binding/_latestVersion)

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Android%20Binding%20Library-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/1456)

## How to use

```java
dependencies {
    ...
    compile 'com.mobandme:android-bind:1.0+'
}
```
Add the library dependency to your build.gradle file.

```java
class MyModel {

    @BindTo(viewId = R.id.name)
    private String name = "Txus Ballesteros";
    
    ...
}
```
Use @BindTo annotation on your model class fields to configure binding between your model and your UI views.


```java
public class MainActivity extends Activity {
     MyModel myModel;
     ViewGroup rootView;
     Binder myModelBinder;
    
     @Override
     protected void onCreate(Bundle savedInstanceState) {
        ...
        
        myModelBinder = new Binder.Builder()
                                .setSource(myModel)
                                .setDestination(rootView)
                                .build();
                                
        ...
     }
     
     @Override
     protected void onResume() {
        ...
        
        //Use this code to start the data binding process.
        //  In this case execute the binding between your Model Class and your app UI.
        myModelBinder.bind();
        
        //Use this code to start the reversed data binding process.
        //  In this case execute the binding between your app UI and your Model Class.
        myModelBinder.bindReverse();
     }
    ...
}
```
IMPORTANT!! Look the methods setSource and setDestination of the Binder.Builder, these methods establish the data binding direction. For example, 
if you set at source your Model class and set into Destination you ViewGroup, when you call to the bind method, this will be the natural direction of 
the data. And you will need to call bindReverse method to invert the direction of the data.

### Configuring a custom Data Parser

Occasionally you will need adapt the data stored in your model to show this on your app UI or vice-versa. For example, think on a Calendar field. Well, you can 
create your own data parser for example to format this date before to show this on the ui.


First, create your own data parser class.

```java

//IMPORTANT!!, don't forget extend your class from com.mobandme.android.bind.parser.DataParser class.
class CalendarParser extends DataParser {

    @Override
    public Object onParse(Compiler.Mapping mapping, Object value, int direction) {
        Object result = value;
        SimpleDateFormat dateFormatter =  new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        if (direction == Binder.DIRECTION_OBJECT_TO_VIEWS) {
            Calendar date = (Calendar)value;
            result = dateFormatter.format(date.getTime());
        } else {
            try {
                Date date = dateFormatter.parse(value.toString());
                Calendar calendar = GregorianCalendar.getInstance(Locale.getDefault());
                calendar.setTime(date);

                result = calendar;

            } catch (ParseException error) {
            }
        }

        return result;
    }
}
```

Second, configure your custom data parser on your model class fields. And that is all.

```java
class MyModel {

    @BindTo(viewId = R.id.dateOfBirth, parser = CalendarParser.class )
    private Calendar dateOfBirth;

    ...
}
```

### Configuring a custom Data Binder

When you use for example a custom views or a view not supported by default by the library, the you need a custom Data Binder. This class allow to 
manage the logic between de data and the view.

First, create your own data binder class.

```java

//IMPORTANT!!, don't forget extend your class from com.mobandme.android.bind.binder.DataBinder class.
class MyCustomViewBinder extends DataBinder {

    @Override
    public void onBind(Compiler.Mapping mapping, Object object, View view, int direction) {
        String value = mapping.getField()...
        
        if (direction == Binder.DIRECTION_OBJECT_TO_VIEWS) {
            ((MyCustomView)view).setMyCustomProperty(value);
        } else {
             mapping.getField().set(object, ((MyCustomView)view).getMyCustomProperty());
        }
    }
    
    ...
}
```

Second, configure your custom data binder on your model class fields. And that is all.

```java
class MyModel {

    @BindTo(viewId = R.id.myCustomView, binder = MyCustomViewBinder.class )
    private Calendar dateOfBirth;

    ...
}
```

## Proguard Configuration

If you use Proguard to obfuscate your project, don't forget put this configuration into your proguard-rules.pro file.

```java
-keep class com.mobandme.android.bind.** { *; }
-keep class * extends com.mobandme.android.bind.parser.DataParser { *; }
-keep class * extends com.mobandme.android.bind.parser.DataBinder { *; }
-keep @com.mobandme.android.bind.annotations.BindableModel public class * { *; }
-keepnames class * {
    @com.mobandme.android.bind.annotations.BindTo *;
    @com.mobandme.android.bind.annotations.Bindings *;
    @com.mobandme.android.bind.annotations.BindableModel *;
}
```

**IMPORTANT!!, If you use Proguard, you will need mark your model classes with @BindableModel annotation.**

```java
@BindableModel
public class MyModel {
    ...
}
```

## License

Copyright 2015 Txus Ballesteros

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

