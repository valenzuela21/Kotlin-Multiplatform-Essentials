//
//  MainViewModel.swift
//  iosApp
//
//  Created by Jorge Maldonado Borbon on 27/09/25.
//

import Foundation
import Shared

class MainViewModel: ObservableObject {
    let viewModel = SharedViewModel()
    @Published var articles : [Articles] = []
    @Published var total : Double = 0.0

    init(){
        StateFlowWrapper<AnyObject>(flow: viewModel.articles).watch { [weak self] items in
            self?.articles = items as! [Articles]
        }

        StateFlowWrapper<AnyObject>(flow: viewModel.total).watch { [weak self] value in
            self?.total = value as! Double
        }
    }

    func addArticle(article: String, price: Double){
        viewModel.addArticle(article: article, price: price)
    }

    func deleteArticle(id:Int){
        viewModel.deleteArticle(id: Int32(id))
    }

}