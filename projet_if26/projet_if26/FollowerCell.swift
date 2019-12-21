//
//  FollowerCell.swift
//  projet_if26
//
//  Created by if26-grp1 on 16/12/2019.
//  Copyright © 2019 if26. All rights reserved.
//

import UIKit

class FollowerCell: UITableViewCell {

    @IBOutlet weak var nameLabel: UILabel!
    @IBOutlet weak var levelLabel: UILabel!
    @IBOutlet weak var dateLabel: UILabel!
    
    func setFollower(follower: Follower) {
        nameLabel.text = follower.name
        levelLabel.text = String(follower.level)
        
        let date = follower.date
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "yyyy-MM-dd"
        let dateString = dateFormatter.string(from: date)
        
        dateLabel.text = dateString
    }

}
