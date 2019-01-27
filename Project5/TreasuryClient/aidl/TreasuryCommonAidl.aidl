// TreasuryCommonAidl.aidl
package com.gupta.sudhanshu.cs478.TreasuryCommon;

// Declare any non-default types here with import statements

interface TreasuryCommonAidl {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */

    int[] monthlyAvgCash(int aYear);
    int[] dailyCash(int aYear, int aMonth, int aDay, int aNumber);
}