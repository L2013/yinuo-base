enum PriceAdjustmentTaskStatusEnum {
        /**
         * 开始状态
         */
        None,
        /**
         * 待商家处理
         */
        Supplier_Processing,
        /**
         * 待控商小二处理
         */
        Supplier_Manager_Processing,
        /**
         * 待价格管控小二处理
         */
        Price_Manager_Processing,
        /**
         * 退出
         */
        Closed
    }