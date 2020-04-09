# TextViewRichDrawable [![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-TextViewRichDrawable-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/5002)

This is a tiny library which empowers TextView's (and its inheritors) compound drawables with size specifying, vector support and tinting. Currently empowers next Android views with RichDrawable support:

 * **Button** -> ButtonRichDrawable
 * **CheckBox** -> CheckBoxRichDrawable
 * **EditText** -> EditTextRichDrawable
 * **RadioButton** -> RadioButtonRichDrawable
 * **TextView** -> TextViewRichDrawable

This library is just an extension of Android's TextView.

## Usage

* Enable vector Drawable support for pre Lollipop devices ([more details](https://developer.android.com/guide/topics/graphics/vector-drawable-resources.html#vector-drawables-backward-solution)): 

```groovy
 android {
   defaultConfig {
     vectorDrawables.useSupportLibrary = true
    }
 }
```

* In XML layout: 

```xml
    <com.tolstykh.textviewrichdrawable.TextViewRichDrawable
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Some text"
        app:compoundDrawableHeight="24dp"
        app:compoundDrawableWidth="24dp"
        app:drawableTopVector="@drawable/some_vector_drawble"
        app:drawableEndVector="@drawable/another_vector_drawable"
        app:drawableTint="@color/colorAccent" />
```        

* All customizable attributes:

```xml
    <declare-styleable name="TextViewRichDrawable">
        <attr name="compoundDrawableWidth" format="dimension"/>
        <attr name="compoundDrawableHeight" format="dimension"/>
        <attr name="drawableStartVector" format="reference" />
        <attr name="drawableTopVector" format="reference" />
        <attr name="drawableEndVector" format="reference" />
        <attr name="drawableBottomVector" format="reference" />
        <attr name="drawableTint" format="reference" />
    </declare-styleable>
```

## Example

![TextView-rich-drawable](demo_screenshot.png)

## Download

```groovy
repositories {
    jcenter()
}
```

```groovy
compile 'com.tolstykh.textviewrichdrawable:textview-rich-drawable:0.3.2'
```
## V2 Is in Progress
```xml
<com.tolstykh.textviewrichdrawable.TextViewRichDrawable
        android:id="@+id/textView"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:gravity="center"
        android:text="TextViewRichDrawable: WITH SIZE\nAll Drawable is 48 only Only Bottom is 24"
        app:compoundDrawableBottomHeight="24dp"
        app:compoundDrawableBottomWidth="24dp"
        app:compoundDrawableEndHeight="56dp"
        app:compoundDrawableEndWidth="56dp"
        app:compoundDrawableHeight="48dp"
        app:compoundDrawableStartHeight="36dp"
        app:compoundDrawableStartWidth="36dp"
        app:compoundDrawableTopHeight="12dp"
        app:compoundDrawableTopWidth="12dp"
        app:compoundDrawableWidth="48dp"
        app:drawableBottomCompat="@drawable/ic_png_bottom"
        app:drawableEndCompat="@drawable/ic_png_end"
        app:drawableStartCompat="@drawable/ic_png_start"
        app:drawableTint="@color/colorAccent"
        app:drawableTopCompat="@drawable/ic_png_top"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:drawableTintBottom="@color/bottom"
        app:drawableTintTop="@color/top"
        app:drawableTintStart="@color/start"
        app:drawableTintEnd="@color/end"
        app:widthHeightAllSame="true" />
```
## License

    Copyright 2016 Oleksandr Tolstykh

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.