//
//  MatchMetaCell.swift
//  projet_if26
//
//  Created by if26-grp1 on 17/12/2019.
//  Copyright © 2019 if26. All rights reserved.
//

import UIKit

class MatchMetaCell: UITableViewCell {
    
    @IBOutlet weak var matchIdLabel: UILabel!
    @IBOutlet weak var nameLabel: UILabel!
    @IBOutlet weak var dateLabel: UILabel!
    @IBOutlet weak var championLabel: UILabel!
    @IBOutlet weak var resultLabel: UILabel!
    @IBOutlet weak var durationLabel: UILabel!
    
    func setMatchMeta(matchMeta: Matchmeta) {
        matchIdLabel.text = matchMeta.matchId
        nameLabel.text = matchMeta.name
        dateLabel.text = matchMeta.date
        championLabel.text = matchMeta.champion
        resultLabel.text = matchMeta.result
        durationLabel.text = matchMeta.duration
        
        self.backgroundColor = matchMeta.result == "Red" ? hexStringToUIColor(hex: "#f22613") : hexStringToUIColor(hex: "#19b5fe")
    }
    
    func setColor() {
        print("setColor invoked")
        // super.backgroundView?.backgroundColor = UIColor.orange
    }
    
    func hexStringToUIColor (hex:String) -> UIColor {
        var cString:String = hex.trimmingCharacters(in: .whitespacesAndNewlines).uppercased()
        
        if (cString.hasPrefix("#")) {
            cString.remove(at: cString.startIndex)
        }
        
        if ((cString.count) != 6) {
            return UIColor.gray
        }
        
        var rgbValue:UInt64 = 0
        Scanner(string: cString).scanHexInt64(&rgbValue)
        
        return UIColor(
            red: CGFloat((rgbValue & 0xFF0000) >> 16) / 255.0,
            green: CGFloat((rgbValue & 0x00FF00) >> 8) / 255.0,
            blue: CGFloat(rgbValue & 0x0000FF) / 255.0,
            alpha: CGFloat(1.0)
        )
    }
    
}
