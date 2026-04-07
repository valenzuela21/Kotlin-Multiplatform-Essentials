//
//  HomeView.swift
//  iosApp
//
//  Created by Jorge Maldonado Borbon on 27/09/25.
//

import SwiftUI

struct HomeView: View {

    @StateObject private var state = MainViewModel()
    @State private var showModal = false
    var body: some View {
        NavigationStack {
            Text("Total: \(state.total.formatted(.currency(code: "MXN")))")
                .font(.title)
                .bold()
                .navigationBarTitle("Shopping List").navigationBarTitleDisplayMode(.inline)
                .toolbar {
                    ToolbarItem(placement: .topBarTrailing) {
                        Button(action: {
                            showModal = true
                        }) {
                            Image(systemName: "plus")
                        }
                    }
                }
            .sheet(isPresented: $showModal){
                ModalForm(state:state)
                    .presentationDetents([.medium, .large])
            }



            if state.articles.isEmpty {
                ContentUnavailableView(
                    "No Items",
                    systemImage: "cart.badge.plus",
                    description: Text("Add some products to get started")
                )
            } else {
                List {
                    ForEach(state.articles, id: \.self) { item in
                        VStack(alignment: .leading) {
                            Text(item.article).font(.headline)
                            Text(item.price.formatted(.currency(code: "MXN"))).font(.subheadline)
                        }
                    }.onDelete { indexSet in
                        for index in indexSet {
                            let item = state.articles[index]
                            state.deleteArticle(id: Int(item.id))
                        }
                    }
                }
            }

        }
    }
}


