package com.demo.freemarker.java;

/**
 * 在内部，模板中可用的变量都是实现了 freemarker.template.TemplateModel 接口的Java对象。
 * 但在数据模型中，可以使用基本的Java集合类作为变量，因为这些变量会在内部被替换为适当的 TemplateModel 类型。
 */
public class F2_DataModel {

    /**
     *  有4种类型的标量：
     *      布尔值
     *      数字
     *      字符串 -- SimpleScalar
     *      日期类型(日期，时间，日期-时间)
     */

    /**
     *  有3种容器：
     *      哈希表 -- TemplateHashModel的SimpleHash
     *      序列 -- TemplateSequenceModel的SimpleSequence
     *      集合 -- TemplateCollectionModel的SimpleCollection
     */

}
