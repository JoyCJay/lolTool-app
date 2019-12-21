//
//  MatchMetaTableViewController.swift
//  projet_if26
//
//  Created by if26-grp1 on 17/12/2019.
//  Copyright © 2019 if26. All rights reserved.
//

import UIKit
import CoreData

class MatchMetaTableViewController: UITableViewController, UIPickerViewDelegate, UIPickerViewDataSource {
    
    var summonerName = ""
    var MatchMetas: [NSManagedObject] = []
    
    @IBOutlet weak var followerPicker: UIPickerView!
    var pickerMenu = ["Item 1", "Item 2", "Item 3"]
    var selectedFollower : String?
    @IBOutlet weak var backBtn: UIButton!
    
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        return pickerMenu.count
    }
    
    
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return 1
    }
    
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        return pickerMenu[row]
    }
    
    func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
        selectedFollower = pickerMenu[row] as String
        print(selectedFollower!)
        self.MatchMetas = createArray(sn: selectedFollower!)
        self.tableView.reloadData()
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.followerPicker.delegate = self
        self.followerPicker.dataSource = self
        
        self.summonerName = followers[myIndex].value(forKey: "summonerName") as! String
        print("summonerName: ",self.summonerName)
        
        self.pickerMenu = getFollowerNams()
        self.followerPicker.selectRow(myIndex, inComponent: 0, animated: true)
        MatchMetas = createArray(sn: self.summonerName)
    }
    
    func getFollowerNams() -> [String] {
        var arr : [String] = []
        
        let appDelegate = UIApplication.shared.delegate as! AppDelegate
        let context = appDelegate.persistentContainer.viewContext
        let request = NSFetchRequest<NSFetchRequestResult>(entityName: "Followers")
        request.returnsObjectsAsFaults = false
        
        do {
            let result = try context.fetch(request)
            if result.count > 0 {
                for r in result as! [NSManagedObject] {
                    let name = r.value(forKey: "summonerName") as! String
                    arr.append(name)
                }
            }
        } catch  {
            
        }
        return arr
    }
    
    
    func createArray(sn: String) -> [NSManagedObject] {
        
        var tempMatchMetas: [NSManagedObject] = []
        
        let appDelegate = UIApplication.shared.delegate as! AppDelegate
        let context = appDelegate.persistentContainer.viewContext
        //
        let predicate = NSPredicate(format: "summonerName == %@", sn)
        
        let fetchRequest = NSFetchRequest<NSFetchRequestResult>(entityName: "MatchMeta")
        fetchRequest.predicate = predicate
        
        do {
            tempMatchMetas = try context.fetch(fetchRequest) as! [NSManagedObject]
            print(tempMatchMetas.count)
        } catch {
            
        }
        return tempMatchMetas        
    }

    // MARK: - Table view data source

    override func numberOfSections(in tableView: UITableView) -> Int {
        // #warning Incomplete implementation, return the number of sections
        return 1
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // #warning Incomplete implementation, return the number of rows
        return 5
    }

    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {

        let object = MatchMetas[indexPath.row]

        let cell = tableView.dequeueReusableCell(withIdentifier: "matchMetaCell") as! MatchMetaCell
        
        // Configure the cell...
        
        let matchId = object.value(forKey: "matchId")
        let summonerName = object.value(forKey: "summonerName")
        let champion = object.value(forKey: "champion")
        let matchDate = object.value(forKey: "matchDate")
        let duration = object.value(forKey: "duration")
        let result = object.value(forKey: "result")
        
        let matchMeta = Matchmeta(matchId: matchId as! String, name: summonerName as! String, date: matchDate as! String, champion: champion as! String, result: result as! String, duration: duration as! String)
        
        cell.setMatchMeta(matchMeta: matchMeta)
        
        return cell
    }
    
    @IBAction func backOnAction(_ sender: UIButton) {
        performSegue(withIdentifier: "segueBack", sender: self)
    }
}
