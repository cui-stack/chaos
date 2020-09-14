<?php
// +----------------------------------------------------------------------
// | ThinkPHP [ WE CAN DO IT JUST THINK ]
// +----------------------------------------------------------------------
// | Copyright (c) 2006~2018 http://thinkphp.cn All rights reserved.
// +----------------------------------------------------------------------
// | Licensed ( http://www.apache.org/licenses/LICENSE-2.0 )
// +----------------------------------------------------------------------
// | Author: liu21st <liu21st@gmail.com>
// +----------------------------------------------------------------------
use think\facade\Route;

Route::get('info/add/:data', 'info/one');

Route::get('info/delete/:mu', 'info/delete');

Route::get('info/update/:mu/:data', 'info/update');

Route::get('info/one/:mu', 'info/one');

Route::get('info/list/:data', 'info/list');

Route::get('info/page/:page', 'info/page');
