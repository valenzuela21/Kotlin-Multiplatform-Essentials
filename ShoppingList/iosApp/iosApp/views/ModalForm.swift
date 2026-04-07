//
// Created by Jorge Maldonado Borbon on 27/09/25.
//

import Foundation
import SwiftUI
struct ModalForm : View {
    @StateObject var state : MainViewModel
    @Environment(\.dismiss) var dismiss
    @State private var article : String = ""
    @State private var price : String = ""
    var body: some View {
        NavigationStack{
            Form{
                Section("New article"){
                    TextField("Article", text:$article)
                    TextField("Price", text:$price)
                        .keyboardType(.decimalPad)
                }
                Section {
                    Button("Save"){
                        if !article.isEmpty && !price.isEmpty {
                            let price = Double(price) ?? 0.0
                            state.addArticle(article: article, price: price)
                        }
                        dismiss()
                    }
                    .frame(maxWidth: .infinity)
                    .padding()
                    .background(Color.green)
                    .foregroundColor(.white)
                    .cornerRadius(10)
                }
            }.navigationBarTitle("Add Article")
        }
    }
}