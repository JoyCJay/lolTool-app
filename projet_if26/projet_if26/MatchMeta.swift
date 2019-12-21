//
//  MatchMeta.swift
//  projet_if26
//
//  Created by if26-grp1 on 17/12/2019.
//  Copyright © 2019 if26. All rights reserved.
//

import Foundation
import CoreData
import UIKit

class Matchmeta {
    
    var matchId: String
    var name: String
    var date: String
    var champion: String
    var result: String
    var duration: String
    
    init(matchId: String, name: String, date: String, champion: String, result: String, duration: String) {
        
        self.matchId = matchId
        self.name = name
        self.date = date
        self.champion = champion
        self.result = result
        self.duration = duration
    }
    
}
