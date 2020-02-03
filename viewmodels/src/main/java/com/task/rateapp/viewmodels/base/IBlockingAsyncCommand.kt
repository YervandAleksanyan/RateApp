package com.task.rateapp.viewmodels.base


interface IBlockingAsyncCommand : IAsyncCommand {
    var isBlocked: Boolean
}