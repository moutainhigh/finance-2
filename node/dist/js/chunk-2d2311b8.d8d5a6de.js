(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2d2311b8"],{eeac:function(e,t,o){"use strict";o.r(t);var s=function(){var e=this,t=e.$createElement,o=e._self._c||t;return o("div",[o("a-modal",{attrs:{title:"温馨提示",centered:"",keyboard:!1,closable:!1,maskClosable:!1,footer:null},model:{value:e.show,callback:function(t){e.show=t},expression:"show"}},[o("p",[e._v(e._s(e.$route.params.msg))]),o("p",[e._v(e._s(e.second)+"秒后，跳转...")])])],1)},a=[],n={data:function(){return{show:!0,second:3}},methods:{toPage:function(){var e=this.$route.params.path?this.$route.params.path:"/";this.$router.push({path:e})}},mounted:function(){var e=this;setInterval((function(){e.second--,0==e.second&&e.toPage()}),1e3)}},r=n,u=o("2877"),c=Object(u["a"])(r,s,a,!1,null,null,null);t["default"]=c.exports}}]);