enum PriceAdjustmentTaskEventEnum {

        // 系统事件
        Create,
        Normal_Update,
        /**
         * 合理价变更
         */
        P0_Changed,
        /**
         * 页面价变合理
         */
        Page_Price_changed,

        // 商家事件
        Supplier_Reject,
        Supplier_Agree,
        Supplier_Timeout,

        // 控商小二事件
        Apply_Over_P0_Sell,

        // 价格小二事件
        Agree_Over_P0_Sell,
        Reject_Over_P0_Sell;

        public boolean isSupplierTimeout() {
            return this == Supplier_Timeout;
        }

        public boolean isSystemEvent(){
            return  this == Create ||
                    this == Normal_Update ||
                    this == P0_Changed ||
                    this == Page_Price_changed;
        }
    }