//
//  Follower.swift
//  projet_if26
//
//  Created by if26-grp1 on 16/12/2019.
//  Copyright © 2019 if26. All rights reserved.
//

import Foundation
import CoreData
import UIKit

class Follower {
    
    var name: String
    var level: Int16
    var date: Date
    
    init(name: String, level: Int16, date: Date) {
    
        self.name = name
        self.level = level
        self.date = date
    }
    
}
