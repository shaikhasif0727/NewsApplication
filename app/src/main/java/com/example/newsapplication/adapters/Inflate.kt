package com.example.newsapplication.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

typealias SetContent<T> = (Activity) -> T

typealias Binding<VB,Data> = (position: Int, rowBinding: VB, Data) -> Unit

typealias onItemClickListener<Data> = (data: Data) -> Unit