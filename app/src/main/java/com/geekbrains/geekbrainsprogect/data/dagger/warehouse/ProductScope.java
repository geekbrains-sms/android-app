package com.geekbrains.geekbrainsprogect.data.dagger.warehouse;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ProductScope {
}
