<?php
declare (strict_types = 1);

namespace app\controller;

use think\Db;
use think\Request;


class Info
{
    public function add($data)
    {
        $data = [
            'title' => 'AJ1',
            'info' => 'AJ1@qq.com'
        ];

        Db::table('iya_info')->insert($data);
    }

    public function delete($mu)
    {
        $result = Db::table('think_tp')->delete('4');

    }

    public function update($mu,$data)
    {
        Db::table('think_tp')->where('id','3')->update([ 'nickname' => 'AJ', 'email' => 'AJ@qq.com' ]);

    }

    public function one()
    {
        $tmp = Db::table('think_tp')->where('email','thinkphp@qq.com')->find();

    }

    public function list()
    {
        $tmp = Db::table('think_tp')->where('email','thinkphp@qq.com')->select();

    }
    public function page()
    {
        //
    }


}
