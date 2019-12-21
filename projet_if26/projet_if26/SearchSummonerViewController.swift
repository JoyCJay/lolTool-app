//
//  SearchSummonerViewController.swift
//  projet_if26
//
//  Created by if26-grp1 on 13/12/2019.
//  Copyright © 2019 if26. All rights reserved.
//

import UIKit
import CoreData

class SearchSummonerViewController: UIViewController, UISearchBarDelegate {

    @IBOutlet weak var searchBar: UISearchBar!
    @IBOutlet weak var nameLabel: UILabel!
    @IBOutlet weak var levelLabel: UILabel!
    @IBOutlet weak var dateLabel: UILabel!
    @IBOutlet weak var followBtn: UIButton!
    
    
    var result: [Any] = []
    var searchedSummoner: NSManagedObject? = nil;
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        initData()
        searchBar.delegate = self

        let appDelegate = UIApplication.shared.delegate as! AppDelegate
        let context = appDelegate.persistentContainer.viewContext
        
        let fetchRequest = NSFetchRequest<NSFetchRequestResult>(entityName: "Summoners")
        
        do {
            result = try context.fetch(fetchRequest)
            if result.count>0 {
                for r in result as! [NSManagedObject] {
                    context.delete(r)
                }
            }
            print("has existed", result.count, "summoners")
            print("summoners clean up")
        } catch {
            
        }
        
        let newSummoner = NSEntityDescription.insertNewObject(forEntityName: "Summoners", into: context)
        newSummoner.setValue("JoyCJay", forKey: "summonerName")
        newSummoner.setValue(111, forKey: "level")
        newSummoner.setValue(Date(), forKey: "revisionDate")
        
        let newSummoner2 = NSEntityDescription.insertNewObject(forEntityName: "Summoners", into: context)
        newSummoner2.setValue("haoyang", forKey: "summonerName")
        newSummoner2.setValue(33, forKey: "level")
        newSummoner2.setValue(Date(), forKey: "revisionDate")
        
        let newSummoner3 = NSEntityDescription.insertNewObject(forEntityName: "Summoners", into: context)
        newSummoner3.setValue("maybe", forKey: "summonerName")
        newSummoner3.setValue(145, forKey: "level")
        newSummoner3.setValue(Date(), forKey: "revisionDate")
        
        let newSummoner4 = NSEntityDescription.insertNewObject(forEntityName: "Summoners", into: context)
        newSummoner4.setValue("ame", forKey: "summonerName")
        newSummoner4.setValue(211, forKey: "level")
        newSummoner4.setValue(Date(), forKey: "revisionDate")
        
        let newSummoner5 = NSEntityDescription.insertNewObject(forEntityName: "Summoners", into: context)
        newSummoner5.setValue("fy", forKey: "summonerName")
        newSummoner5.setValue(101, forKey: "level")
        newSummoner5.setValue(Date(), forKey: "revisionDate")
        
        do {
            try context.save()
            print("summoners initialized")
        } catch {
            print("sumoners initialized error")
        }
    }
    
    func initData() {
        var keys:[String] = ["matchId", "summonerName", "champion", "matchDate", "duration", "result"]
        let rawData = [
            ["4252392862", "JoyCJay", "81", "2019-10-29 08:49:18", "17 mins", "Red"],
            ["4263199914", "JoyCJay", "114", "2019-11-04 21:15:13", "19 mins", "Blue"],
            ["4274556047", "JoyCJay", "121", "2019-11-12 00:27:41", "3 mins" , "Red"],
            ["4274609270", "JoyCJay", "121", "2019-11-12 00:48:58", "17 mins", "Blue"],
            ["4286905204", "JoyCJay", "121", "2019-11-20 06:18:55", "35 mins", "Red"],
            
            ["4252392862", "haoyang", "81", "2019-10-29 08:49:18", "22 mins", "Red"],
            ["4263199914", "haoyang", "123", "2019-11-04 21:15:13", "19 mins", "Blue"],
            ["4274556047", "haoyang", "107", "2019-11-12 00:27:41", "3 mins" , "Red"],
            ["4274609270", "haoyang", "121", "2019-11-12 00:48:58", "22 mins", "Blue"],
            ["4286905204", "haoyang", "121", "2019-11-20 06:18:55", "35 mins", "Red"],
            
            ["4252392862", "fy", "81", "2019-10-29 08:49:18", "22 mins", "Red"],
            ["4263199914", "fy", "123", "2019-11-04 21:15:13", "26 mins", "Blue"],
            ["4274556047", "fy", "35", "2019-11-12 00:27:41", "33 mins" , "Red"],
            ["4274609270", "fy", "121", "2019-11-12 00:48:58", "18 mins", "Blue"],
            ["4286905204", "fy", "6", "2019-11-20 06:18:55", "35 mins", "Red"],
            
            ["4252392862", "ame", "38", "2019-10-29 08:49:18", "45 mins", "Red"],
            ["4263199914", "ame", "123", "2019-11-04 21:15:13", "26 mins", "Blue"],
            ["4274556047", "ame", "35", "2019-11-12 00:27:41", "38 mins" , "Red"],
            ["4274609270", "ame", "76", "2019-11-12 00:48:58", "18 mins", "Blue"],
            ["4286905204", "ame", "6", "2019-11-20 06:18:55", "35 mins", "Red"],
            
            ["4252392862", "maybe", "48", "2019-10-29 08:49:18", "45 mins", "Red"],
            ["4263199914", "maybe", "26", "2019-11-04 21:15:13", "26 mins", "Blue"],
            ["4274556047", "maybe", "19", "2019-11-12 00:27:41", "48 mins" , "Red"],
            ["4274609270", "maybe", "76", "2019-11-12 00:48:58", "18 mins", "Blue"],
            ["4286905204", "maybe", "127", "2019-11-20 06:18:55", "19 mins", "Red"]
        ]
        
        let appDelegate = UIApplication.shared.delegate as! AppDelegate
        let context = appDelegate.persistentContainer.viewContext
        
        for match in rawData {
            let newMatch = NSEntityDescription.insertNewObject(forEntityName: "MatchMeta", into: context)
            for i in 0..<6 {
                newMatch.setValue(match[i], forKey: keys[i])
            }
            do {
                try context.save()
                print("match", match[0], match[1], " saved")
            } catch  {
                print("match", match[0], match[1], " save err")
            }
        }
    }
    
    func searchBarSearchButtonClicked(_ searchBar: UISearchBar) {
        let searchText = searchBar.text!
        
        let appDelegate = UIApplication.shared.delegate as! AppDelegate
        let context = appDelegate.persistentContainer.viewContext
        
        let predicate = NSPredicate(format: "summonerName == %@", searchText)
        let fetchRequest = NSFetchRequest<NSFetchRequestResult>(entityName: "Summoners")
        fetchRequest.predicate = predicate
        
        do {
            result = try context.fetch(fetchRequest)
            
            if result.count == 1 {
                searchedSummoner = result[0] as! NSManagedObject
                nameLabel.text = searchedSummoner!.value(forKey: "summonerName") as? String
                levelLabel.text = String(searchedSummoner!.value(forKey: "level") as! Int16)
                
                let date = searchedSummoner!.value(forKey: "revisionDate") as! Date
                let dateFormatter = DateFormatter()
                dateFormatter.dateFormat = "yyyy-MM-dd"
                let dateString = dateFormatter.string(from: date)
                
                dateLabel.text = String(dateString)
                followBtn.isEnabled = true
            }
            else if result.count == 0{
                followBtn.isEnabled = false
                createPop(message: "summoner not exisit")
            }
            else{
                followBtn.isEnabled = false
                createPop(message: "already in your followers list")
            }
        } catch {
            
        }
    }
    
    func createPop(message: String) {
        let alert = UIAlertController(title: "Alert", message: message, preferredStyle: UIAlertController.Style.alert)
        alert.addAction(UIAlertAction(title: "OK", style: .default, handler: { action in
            switch action.style{
            case .default:
                break
            case .cancel:
                break
            case .destructive:
                break
            }
        }))
        self.present(alert, animated: true, completion: nil)
    }
    
    @IBAction func followOnClick(_ sender: Any) {
        let appDelegate = UIApplication.shared.delegate as! AppDelegate
        let context = appDelegate.persistentContainer.viewContext
        
        if(!isEntityAttributeExists(summonerName: nameLabel.text!, entityName: "Followers")) {
            let newFollower = NSEntityDescription.insertNewObject(forEntityName: "Followers", into: context)
            newFollower.setValue(nameLabel.text, forKey: "summonerName")
            
            let l = searchedSummoner?.value(forKey: "level") as! Int16
            newFollower.setValue(l, forKey: "level")
            
            //let dateString =
            let d = searchedSummoner?.value(forKey: "revisionDate") as! Date
            newFollower.setValue(d, forKey: "revisionDate")
            
            do {
                try context.save()
                createPop(message: nameLabel.text!+" has added to your following!")
                print("follower", nameLabel.text!, "saved")
            } catch {
                print("followers", nameLabel.text!, "save error")
            }
        } else {
            createPop(message: "follower "+nameLabel.text!+" has existed!")
        }
        
        
    }
    
    func isEntityAttributeExists(summonerName: String, entityName: String) -> Bool{
        let appDelegate = UIApplication.shared.delegate as! AppDelegate
        let context = appDelegate.persistentContainer.viewContext
        
        let fetchRequest = NSFetchRequest<NSFetchRequestResult>(entityName: "Followers")
        
        fetchRequest.predicate = NSPredicate(format: "summonerName == %@", summonerName)
        let res = try! context.fetch(fetchRequest)
        return res.count > 0 ? true : false
        
    }
    

    
    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */

}
