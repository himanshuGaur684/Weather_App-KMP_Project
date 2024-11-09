//
//  Helper.swift
//  iosApp
//
//  Created by Himanshu Gaur on 09/11/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

func collect<T>(stateFlow:CommonStateFlow<T>,onEach: @escaping (T)->Void) async{

    var collectionCancelled : CheckedContinuation<Void,Never>?
    
    
    let cancellable = stateFlow.startCollect(onEach: {value in onEach(value!)}, onCancel: {collectionCancelled?.resume()})
    
    await withTaskCancellationHandler(operation: { await withCheckedContinuation{continuation in collectionCancelled = continuation}}, onCancel: {cancellable.cancel()})
    
}
