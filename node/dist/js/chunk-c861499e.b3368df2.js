(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-c861499e"],{5530:function(t,a,e){"use strict";e.d(a,"a",(function(){return n}));e("a4d3"),e("4de4"),e("4160"),e("e439"),e("dbb4"),e("b64b"),e("159b");function s(t,a,e){return a in t?Object.defineProperty(t,a,{value:e,enumerable:!0,configurable:!0,writable:!0}):t[a]=e,t}function i(t,a){var e=Object.keys(t);if(Object.getOwnPropertySymbols){var s=Object.getOwnPropertySymbols(t);a&&(s=s.filter((function(a){return Object.getOwnPropertyDescriptor(t,a).enumerable}))),e.push.apply(e,s)}return e}function n(t){for(var a=1;a<arguments.length;a++){var e=null!=arguments[a]?arguments[a]:{};a%2?i(Object(e),!0).forEach((function(a){s(t,a,e[a])})):Object.getOwnPropertyDescriptors?Object.defineProperties(t,Object.getOwnPropertyDescriptors(e)):i(Object(e)).forEach((function(a){Object.defineProperty(t,a,Object.getOwnPropertyDescriptor(e,a))}))}return t}},"973c":function(t,a,e){},c84b:function(t,a,e){"use strict";e.r(a);var s=function(){var t=this,a=t.$createElement,e=t._self._c||a;return e("div",{staticClass:"guquan"},[e("div",{staticClass:"top"},[e("Header")],1),e("div",{staticClass:"list-box"},[e("div",{staticClass:"list-filter"},[e("DetailItem",{staticClass:"list-item",attrs:{item:t.detail}})],1)]),e("div",{staticClass:"list-box"},[e("div",{staticClass:"list-filter"},[t._m(0),e("div",{staticClass:"detail-body"},[e("div",{staticClass:"b-item"},[e("span",[t._v("注册地址：")]),e("span",[t._v(t._s(t.detail.registerAddressStr))])]),e("div",{staticClass:"b-item"},[e("span",[t._v("融资阶段：")]),e("span",[t._v(t._s(t.detail.financeStateStr))])]),e("div",{staticClass:"b-item"},[e("span",[t._v("融资额度：")]),e("span",[t._v(t._s(t.detail.financeQuotaStr))])]),e("div",{staticClass:"b-item"},[e("span",[t._v("行业方向：")]),e("span",[t._v(t._s(t.detail.industryDirectStr))])]),e("div",{staticClass:"b-item"},[e("span",[t._v("股东背景：")]),e("span",[t._v(t._s(t.detail.shareholderStr))])]),e("div",{staticClass:"b-item"},[e("span",[t._v("产品阶段：")]),e("span",[t._v(t._s(t.detail.productStateStr))])]),e("div",{staticClass:"b-item"},[e("span",[t._v("营业收入：")]),e("span",[t._v(t._s(t.detail.businessStr))])]),e("div",{staticClass:"b-item"},[e("span",[t._v("营业收入增长率：")]),e("span",[t._v(t._s(t.detail.businessAddRateStr))])]),e("div",{staticClass:"b-item"},[e("span",[t._v("产品毛利率/预期产品毛利率：")]),e("span",[t._v(t._s(t.detail.productRateStr))])]),e("div",{staticClass:"b-item"},[e("span",[t._v("净利润：")]),e("span",[t._v(t._s(t.detail.netInterestRateStr))])]),e("div",{staticClass:"b-item"},[e("span",[t._v("过往融资金额：")]),e("span",[t._v(t._s(t.detail.oldFinanceQuotaStr))])]),e("div",{staticClass:"b-item"},[e("span",[t._v("实际控制人创业经历：")]),e("span",[t._v(t._s(t.detail.experienceStr))])]),e("div",{staticClass:"b-item"},[e("span",[t._v("员工人数：")]),e("span",[t._v(t._s(t.detail.staffCountStr))])]),e("div",{staticClass:"b-item"},[e("span",[t._v("目标市场容量：")]),e("span",[t._v(t._s(t.detail.marketCapacityStr))])]),e("div",{staticClass:"b-item"},[e("span",[t._v("目标市场增长率：")]),e("span",[t._v(t._s(t.detail.marketAddRateStr))])]),e("div",{staticClass:"b-item"},[e("span",[t._v("目标客户：")]),e("span",[t._v(t._s(t.detail.targetCustomerStr))])]),e("div",{staticClass:"b-item"},[e("span",[t._v("市场占有率/预期市场占有率：")]),e("span",[t._v(t._s(t.detail.marketOccupyRateStr))])]),e("div",{staticClass:"b-item"},[e("span",[t._v("是否接受回购条款：")]),e("span",[t._v(t._s(t.detail.boolBuyBackStr))])]),e("div",{staticClass:"b-item"},[e("span",[t._v("发明专利数量")]),e("span",[t._v(t._s(t.detail.patentCountStr))])]),e("div",{staticClass:"b-item"},[e("span",[t._v("公司竞争优势：")]),e("span",[t._v(t._s(t.detail.advantageStr))])]),e("div",{staticClass:"b-item"},[e("span",[t._v("股东累计投入资金：")]),e("span",[t._v(t._s(t.detail.capitalsStr))])]),e("div",{staticClass:"b-item"},[e("span",[t._v("公司所获评定称号：")]),e("span",[t._v(t._s(t.detail.evaluateNameStr))])])])])]),e("Footer")],1)},i=[function(){var t=this,a=t.$createElement,e=t._self._c||a;return e("div",{staticClass:"filter-head"},[e("div",{staticClass:"head-title"},[e("img",{staticStyle:{width:"20px",height:"21px","vertical-align":"sub"},attrs:{src:"/image/icon-chaxunliebiao.png",alt:""}}),e("span",[t._v("资质要求")])])])}],n=(e("d3b7"),e("5530")),r={name:"Detail",data:function(){return{detail:{}}},methods:{initPage:function(){var t=this,a=this.$route.query;this.$http.post("/finance/financeStockDetail/getFinanceStockProductDetail",Object(n["a"])({},a)).then((function(a){0==a.data.code&&(t.detail=a.data.content)})).catch((function(t){return console.log(t)}))}},mounted:function(){this.initPage()},components:{Header:function(){return e.e("chunk-68b53840").then(e.bind(null,"0418"))},DetailItem:function(){return e.e("chunk-317b2ac0").then(e.bind(null,"325b"))},Footer:function(){return e.e("chunk-02cc48de").then(e.bind(null,"fd2d"))}}},c=r,l=(e("c919"),e("2877")),d=Object(l["a"])(c,s,i,!1,null,"8df362a4",null);a["default"]=d.exports},c919:function(t,a,e){"use strict";var s=e("973c"),i=e.n(s);i.a},dbb4:function(t,a,e){var s=e("23e7"),i=e("83ab"),n=e("56ef"),r=e("fc6a"),c=e("06cf"),l=e("8418");s({target:"Object",stat:!0,sham:!i},{getOwnPropertyDescriptors:function(t){var a,e,s=r(t),i=c.f,d=n(s),v={},o=0;while(d.length>o)e=i(s,a=d[o++]),void 0!==e&&l(v,a,e);return v}})},e439:function(t,a,e){var s=e("23e7"),i=e("d039"),n=e("fc6a"),r=e("06cf").f,c=e("83ab"),l=i((function(){r(1)})),d=!c||l;s({target:"Object",stat:!0,forced:d,sham:!c},{getOwnPropertyDescriptor:function(t,a){return r(n(t),a)}})}}]);